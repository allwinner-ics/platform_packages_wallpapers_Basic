// Copyright (C) 2009 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

#pragma version(1)

#include "../../../../../frameworks/base/libs/rs/scriptc/rs_types.rsh"
#include "../../../../../frameworks/base/libs/rs/scriptc/rs_math.rsh"
#include "../../../../../frameworks/base/libs/rs/scriptc/rs_graphics.rsh"
//#pragma stateVertex(PVOrtho)
//#pragma stateStore(PSSolid)

#define MAX_PULSES           20
#define MAX_EXTRAS           40
#define PULSE_SIZE           14 // Size in pixels of a cell
#define HALF_PULSE_SIZE      7
#define GLOW_SIZE            64 // Size of the leading glow in pixels
#define HALF_GLOW_SIZE       32
#define SPEED                0.2f // (200 / 1000) Pixels per ms
#define SPEED_VARIANCE       0.3f
#define PULSE_NORMAL         0
#define PULSE_EXTRA          1
#define TRAIL_SIZE           40 // Number of cells in a trail
#define MAX_DELAY	         2000 // Delay between a pulse going offscreen and restarting

typedef struct pulse_s {
    int pulseType;
    float originX;
    float originY;
    int color;
    int startTime;
    float dx;
    float dy;
    int active;
} pulse_t;

static pulse_t gPulses[MAX_PULSES];
static pulse_t gExtras[MAX_EXTRAS];
static int gNow;
static int gWidth;
static int gHeight;
static int gRotate;


int gIsPreview;
float gXOffset;
int gMode;

rs_program_fragment gPFTexture;
rs_program_store gPSBlend;
rs_program_fragment gPFTexture565;
rs_program_vertex gPVOrtho;

rs_allocation gTBackground;
rs_allocation gTPulse;
rs_allocation gTGlow;

#pragma rs export_var(gIsPreview, gXOffset, gMode, gPFTexture, gPSBlend, gPFTexture565, gPVOrtho, gTBackground, gTPulse, gTGlow)


void setColor(int c) {
    //debugPi(99, 6);
    if (gMode == 1) {
        // sholes red
        color(0.9f, 0.1f, 0.1f, 0.8f);
    } else if (c == 0) {
        // red
        color(1.0f, 0.0f, 0.0f, 0.8f);
    } else if (c == 1) {
        // green
        color(0.0f, 0.8f, 0.0f, 0.8f);
    } else if (c == 2) {
        // blue
        color(0.0f, 0.4f, 0.9f, 0.8f);
    } else if (c == 3) {
        // yellow
        color(1.0f, 0.8f, 0.0f, 0.8f);
    }
}

void initPulse(struct pulse_s * pulse, int pulseType) {
    //debugPi(99, 5);
    if (randf(1.f) > 0.5f) {
        pulse->originX = (int)randf(getWidth() * 2 / PULSE_SIZE) * PULSE_SIZE;
        pulse->dx = 0;
        if (randf(1.f) > 0.5f) {
            // Top
            pulse->originY = 0;
            pulse->dy = randf2(1.0f - SPEED_VARIANCE, 1.0 + SPEED_VARIANCE);
        } else {
            // Bottom
            pulse->originY = gHeight;
            pulse->dy = -randf2(1.0f - SPEED_VARIANCE, 1.0 + SPEED_VARIANCE);
        }
    } else {
        pulse->originY = (int)randf(getHeight() / PULSE_SIZE) * PULSE_SIZE;
        pulse->dy = 0;
        if (randf(1.f) > 0.5f) {
            // Left
            pulse->originX = 0;
            pulse->dx = randf2(1.0f - SPEED_VARIANCE, 1.0 + SPEED_VARIANCE);
        } else {
            // Right
            pulse->originX = getWidth() * 2;
            pulse->dx = -randf2(1.0f - SPEED_VARIANCE, 1.0 + SPEED_VARIANCE);
        }
    }
    pulse->startTime = gNow + (int)randf(MAX_DELAY);

    pulse->color = (int)randf(4.0f);

    pulse->pulseType = pulseType;
    if (pulseType == PULSE_EXTRA) {
        pulse->active = 0;
    } else {
        pulse->active = 1;
    }
}

void initPulses() {
    //debugPi(99, 4);
    gNow = uptimeMillis();
    int i;
    for (i=0; i<MAX_PULSES; i++) {
        initPulse(&gPulses[i], PULSE_NORMAL);
    }
    for (i=0; i<MAX_EXTRAS; i++) {
        struct pulse_s * p = &gExtras[i];
        p->pulseType = PULSE_EXTRA;
        p->active = 0;
    }
}

void drawBackground() {
    //debugPi(99, 3);
    bindProgramFragment(gPFTexture565);
    bindTexture(gPFTexture565, 0, gTBackground);
    color(1.0f, 1.0f, 1.0f, 1.0f);
    if (gRotate) {
        drawRect(0.0f, 0.0f, gHeight*2, gWidth, 0.0f);
    } else {
        drawRect(0.0f, 0.0f, gWidth*2, gHeight, 0.0f);
    }
}

void drawPulses(pulse_t * pulseSet, int setSize) {
    //debugPi(99, 2);
	 bindProgramFragment(gPFTexture);
    bindProgramStore(gPSBlend);

    float matrix[16];

    int i;
    for (i=0; i<setSize; i++) {
    	struct pulse_s * p = &pulseSet[i];

 	    int delta = gNow - p->startTime;

    	if (p->active != 0 && delta >= 0) {

	        float x = p->originX + (p->dx * SPEED * delta);
	        float y = p->originY + (p->dy * SPEED * delta);

	        matrixLoadIdentity(matrix);
	        if (p->dx < 0) {
	            vpLoadTextureMatrix(matrix);
	            float xx = x + (TRAIL_SIZE * PULSE_SIZE);
	            if (xx <= 0) {
	                initPulse(p, p->pulseType);
	            } else {
	                setColor(p->color);
	                bindTexture(gPFTexture, 0, gTPulse);
	                drawRect(x, y, xx, y + PULSE_SIZE, 0.0f);
	                bindTexture(gPFTexture, 0, gTGlow);
	                drawRect(x + HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    y + HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    x + HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    y + HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    0.0f);
	            }
	        } else if (p->dx > 0) {
				x += PULSE_SIZE; // need to start on the other side of this cell
	            matrixRotate(matrix, 180.0f, 0.0f, 0.0f, 1.0f);
	            vpLoadTextureMatrix(matrix);
	            float xx = x - (TRAIL_SIZE * PULSE_SIZE);
	 	        if (xx >= gWidth * 2) {
	               initPulse(p, p->pulseType);
	            } else {
	                setColor(p->color);
	                bindTexture(gPFTexture, 0, gTPulse);
	                drawRect(xx, y, x, y + PULSE_SIZE, 0.0f);
	                bindTexture(gPFTexture, 0, gTGlow);
	                drawRect(x - HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    y + HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    x - HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    y + HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    0.0f);
	            }
	        } else if (p->dy < 0) {
	            matrixRotate(matrix, -90.0f, 0.0f, 0.0f, 1.0f);
	            vpLoadTextureMatrix(matrix);
	            float yy = y + (TRAIL_SIZE * PULSE_SIZE);
	            if (yy <= 0) {
	               initPulse(p, p->pulseType);
	            } else {
	                setColor(p->color);
	                bindTexture(gPFTexture, 0, gTPulse);
	                drawRect(x, y, x + PULSE_SIZE, yy, 0.0f);
	                bindTexture(gPFTexture, 0, gTGlow);
	                drawRect(x + HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    y + HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    x + HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    y + HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    0.0f);
	            }
	        } else if (p->dy > 0) {
				y += PULSE_SIZE; // need to start on the other side of this cell
	            matrixRotate(matrix, 90.0f, 0.0f, 0.0f, 1.0f);
	            vpLoadTextureMatrix(matrix);
	            float yy = y - (TRAIL_SIZE * PULSE_SIZE);
	            if (yy >= gHeight) {
	               initPulse(p, p->pulseType);
	            } else {
	                setColor(p->color);
	                bindTexture(gPFTexture, 0, gTPulse);
	                drawRect(x, yy, x + PULSE_SIZE, y, 0.0f);
	                bindTexture(gPFTexture, 0, gTGlow);
	                drawRect(x + HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    y - HALF_PULSE_SIZE - HALF_GLOW_SIZE,
	                    x + HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    y - HALF_PULSE_SIZE + HALF_GLOW_SIZE,
	                    0.0f);
	            }
	        }
	    }
    }

    matrixLoadIdentity(matrix);
    vpLoadTextureMatrix(matrix);
}

void addTap(int x, int y) {
    //debugPi(99, 1);
    int i;
    int count = 0;
    int color = (int)randf(4.0f);
    x = (int)(x / PULSE_SIZE) * PULSE_SIZE;
    y = (int)(y / PULSE_SIZE) * PULSE_SIZE;
    for (i=0; i<MAX_EXTRAS; i++) {
    	struct pulse_s * p = &gExtras[i];
    	if (p->active == 0) {
            p->originX = x;
            p->originY = y;

            if (count == 0) {
                p->dx = 1.5f;
                p->dy = 0.0f;
            } else if (count == 1) {
                p->dx = -1.5f;
                p->dy = 0.0f;
            } else if (count == 2) {
                p->dx = 0.0f;
                p->dy = 1.5f;
            } else if (count == 3) {
                p->dx = 0.0f;
                p->dy = -1.5f;
            }

            p->active = 1;
            p->color = color;
            color++;
            if (color >= 4) {
                color = 0;
            }
            p->startTime = gNow;
            count++;
            if (count == 4) {
                break;
            }
        }
    }
}

int root() {
    //debugPi(99, 0);
    gWidth = getWidth();
    gHeight = getHeight();
    gRotate = gWidth > gHeight ? 1 : 0;

    gNow = uptimeMillis();

    bindProgramVertex(gPVOrtho);

    float matrix[16];
    matrixLoadIdentity(matrix);
    if (gRotate) {
        //matrixLoadRotate(matrix, 90.0f, 0.0f, 0.0f, 1.0f);
        //matrixTranslate(matrix, 0.0f, -height, 1.0f);
        // XXX: HAX: do not slide display in landscape
    } else {
         matrixTranslate(matrix, -(gXOffset * gWidth), 0, 0);
    }

    vpLoadModelMatrix(matrix);

    drawBackground();
    drawPulses(gPulses, MAX_PULSES);
    drawPulses(gExtras, MAX_EXTRAS);

    return 45;
}
