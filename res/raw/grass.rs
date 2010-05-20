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


#define RSID_BLADES_BUFFER 2

#define TESSELATION 0.5f
#define HALF_TESSELATION 0.25f
#define MAX_BEND 0.09f
#define SECONDS_IN_DAY 86400.0f
#define PI 3.1415926f
#define HALF_PI 1.570796326f
#define REAL_TIME 1

int gBladesCount;
int gIndexCount;
int gWidth;
int gHeight;
float gXOffset;
float gDawn;
float gMorning;
float gAfternoon;
float gDusk;
int gIsPreview;
rs_program_vertex gPVBackground;
rs_program_fragment gPFBackground;
rs_program_fragment gPFGrass;
rs_program_store gPSBackground;
rs_allocation gTNight;
rs_allocation gTSunset;
rs_allocation gTSunrise;
rs_allocation gTSky;
rs_allocation gTAa;
rs_mesh gBladesMesh;


typedef struct Blade_s {
    float angle;
    int size;
    float xPos;
    float yPos;
    float offset;
    float scale;
    float lengthX;
    float lengthY;
    float hardness;
    float h;
    float s;
    float b;
    float turbulencex;
} Blade_t;
Blade_t *Blades;

typedef struct Vertex_s {
    uint32_t color;
    float x;
    float y;
    float s;
    float t;
} Vertex_t;
Vertex_t *Verticies;


#pragma rs export_var(gBladesCount, gIndexCount, gWidth, gHeight, gXOffset, gDawn, gMorning, gAfternoon, gDusk, gIsPreview, gPVBackground, gPFBackground, gPFGrass, gPSBackground, gTNight, gTSunset, gTSunrise, gTSky, gTAa, gBladesMesh, Blades, Verticies)


void updateBlades()
{
    Blade_t *bladeStruct = Blades;
    for (int i = 0; i < gBladesCount; i ++) {
        float xpos = rsRand(-gWidth, gWidth);
        bladeStruct->xPos = xpos;
        bladeStruct->turbulencex = xpos * 0.006f;
        bladeStruct->yPos = gHeight;
        bladeStruct++;
    }
}

float time(int isPreview) {
    if (REAL_TIME && !isPreview) {
        return (rsHour() * 3600.0f + rsMinute() * 60.0f + rsSecond()) / SECONDS_IN_DAY;
    }
    float t = rsUptimeMillis() / 30000.0f;
    return t - (int) t;
}

void alpha(float a) {
    color(1.0f, 1.0f, 1.0f, a);
}

static float normf(float start, float stop, float value) {
    return (value - start) / (stop - start);
}

void drawNight(int width, int height) {
    rsgBindTexture(gPFBackground, 0, gTNight);
    rsgDrawQuadTexCoords(
            0.0f, -32.0f, 0.0f,
            0.0f, 1.0f,
            width, -32.0f, 0.0f,
            2.0f, 1.0f,
            width, 1024.0f - 32.0f, 0.0f,
            2.0f, 0.0f,
            0.0f, 1024.0f - 32.0f, 0.0f,
            0.0f, 0.0f);
}

void drawSunrise(int width, int height) {
    rsgBindTexture(gPFBackground, 0, gTSunrise);
    rsgDrawRect(0.0f, 0.0f, width, height, 0.0f);
}

void drawNoon(int width, int height) {
    rsgBindTexture(gPFBackground, 0, gTSky);
    rsgDrawRect(0.0f, 0.0f, width, height, 0.0f);
}

void drawSunset(int width, int height) {
    rsgBindTexture(gPFBackground, 0, gTSunset);
    rsgDrawRect(0.0f, 0.0f, width, height, 0.0f);
}

int drawBlade(Blade_t *bladeStruct, Vertex_t *v,
        float brightness, float xOffset, float now) {

    float scale = bladeStruct->scale;
    float angle = bladeStruct->angle;
    float xpos = bladeStruct->xPos + xOffset;
    int size = bladeStruct->size;
    int color = hsbToAbgr(bladeStruct->h, bladeStruct->s,
                          mix(0.f, bladeStruct->b, brightness), 1.0f);

    float newAngle = (turbulencef2(bladeStruct->turbulencex, now, 4.0f) - 0.5f) * 0.5f;
    angle = clamp(angle + (newAngle + bladeStruct->offset - angle) * 0.15f, -MAX_BEND, MAX_BEND);

    float currentAngle = HALF_PI;

    float bottomX = xpos;
    float bottomY = bladeStruct->yPos;

    float d = angle * bladeStruct->hardness;


    float si = size * scale;
    float bottomLeft = bottomX - si;
    float bottomRight = bottomX + si;
    float bottom = bottomY + HALF_TESSELATION;

    v[0].color = color;                          // V1.ABGR
    v[0].x = bottomLeft;                    // V1.X
    v[0].y = bottom;                        // V1.Y
    v[0].s = 0.f;                           // V1.s
    v[0].t = 0.f;                           // V1.t
                                                    //
    v[1].color = color;                          // V2.ABGR
    v[1].x = bottomRight;                   // V2.X
    v[1].y = bottom;                        // V2.Y
    v[1].s = 1.f;                           // V2.s
    v[1].t = 0.f;                           // V2.t
    v += 2;

    for ( ; size > 0; size -= 1) {
        float topX = bottomX - cos(currentAngle) * bladeStruct->lengthX;
        float topY = bottomY - sin(currentAngle) * bladeStruct->lengthY;

        si = (float)size * scale;
        float spi = si - scale;

        float topLeft = topX - spi;
        float topRight = topX + spi;

        v[0].color = color;                          // V1.ABGR
        v[0].x = topLeft;                       // V1.X
        v[0].y = topY;                          // V1.Y
        v[0].s = 0.f;                           // V1.s
        v[0].t = 0.f;                           // V1.t

        v[1].color = color;                          // V2.ABGR
        v[1].x = topRight;                      // V2.X
        v[1].y = topY;                          // V2.Y
        v[1].s = 1.f;                           // V2.s
        v[1].t = 0.f;                           // V2.t

        v += 2;
        bottomX = topX;
        bottomY = topY;
        currentAngle += d;
    }

    bladeStruct->angle = angle;

    // 2 vertices per triangle, 5 properties per vertex (RGBA, X, Y, S, T)
    return bladeStruct->size * 2 + 2;
}

void drawBlades(float brightness, float xOffset) {
    // For anti-aliasing
    rsgBindTexture(gPFGrass, 0, gTAa);

    Blade_t *bladeStruct = Blades;
    Vertex_t *vtx = Verticies;
    float now = rsUptimeMillis() * 0.00004f;

    for (int i = 0; i < gBladesCount; i += 1) {
        int offset = drawBlade(bladeStruct, vtx, brightness, xOffset, now);
        vtx += offset;
        bladeStruct ++;
    }

    rsgUploadToBufferObject(rsGetAllocation(Verticies));
    rsgDrawSimpleMesh(gBladesMesh, 0, gIndexCount);
}

int root(int launchID) {
    float x = mix((float)gWidth, 0.f, gXOffset);

    float now = time(gIsPreview);
    alpha(1.0f);

    rsgBindProgramVertex(gPVBackground);
    rsgBindProgramFragment(gPFBackground);
    rsgBindProgramStore(gPSBackground);

    float newB = 1.0f;
    if (now >= 0.0f && now < gDawn) {                    // Draw night
        drawNight(gWidth, gHeight);
        newB = 0.0f;
    } else if (now >= gDawn && now <= gMorning) {         // Draw sunrise
        float half = gDawn + (gMorning - gDawn) * 0.5f;
        if (now <= half) {                              // Draw night->sunrise
            drawNight(gWidth, gHeight);
            newB = normf(gDawn, half, now);
            alpha(newB);
            drawSunrise(gWidth, gHeight);
        } else {                                        // Draw sunrise->day
            drawSunrise(gWidth, gHeight);
            alpha(normf(half, gMorning, now));
            drawNoon(gWidth, gHeight);
        }
    } else if (now > gMorning && now < gAfternoon) {      // Draw day
        drawNoon(gWidth, gHeight);
    } else if (now >= gAfternoon && now <= gDusk) {       // Draw sunset
        float half = gAfternoon + (gDusk - gAfternoon) * 0.5f;
        if (now <= half) {                              // Draw day->sunset
            drawNoon(gWidth, gHeight);
            newB = normf(gAfternoon, half, now);
            alpha(newB);
            newB = 1.0f - newB;
            drawSunset(gWidth, gHeight);
        } else {                                        // Draw sunset->night
            drawSunset(gWidth, gHeight);
            alpha(normf(half, gDusk, now));
            drawNight(gWidth, gHeight);
            newB = 0.0f;
        }
    } else if (now > gDusk) {                            // Draw night
        drawNight(gWidth, gHeight);
        newB = 0.0f;
    }

    rsgBindProgramFragment(gPFGrass);
    drawBlades(newB, x);

    return 50;
}
