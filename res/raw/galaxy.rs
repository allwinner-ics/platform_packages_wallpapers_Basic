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

//#pragma stateVertex(PVBkOrtho)
//#pragma stateRaster(parent)
//#pragma stateFragment(PFBackground)
//#pragma stateStore(PFSBackground)

#define ELLIPSE_RATIO 0.892f

#define PI 3.1415f
#define TWO_PI 6.283f
#define ELLIPSE_TWIST 0.023333333f

static float angle;
static int gOldWidth;
static int gOldHeight;
static int gWidth;
static int gHeight;

int gParticlesCount;
int gGalaxyRadius;
float gXOffset;
int gIsPreview;
int gScale;

rs_program_fragment gPFBackground;
rs_program_fragment gPFStars;
rs_program_vertex gPVStars;
rs_program_vertex gPVBkProj;
rs_program_store gPSLights;

rs_allocation gTSpace;
rs_allocation gTFlares;
rs_allocation gTLight1;
rs_allocation gParticlesBuffer;
rs_mesh gParticlesMesh;


struct f3 {
    float x;
    float y;
    float z;
};

typedef struct Particle_s {
    uint32_t color;
    struct f3 position;
} Particle_t;
Particle_t *Particles;


/**
 * Script initialization. Called automatically.
 */
void init() {
    angle = 50.0f;
}

/**
 * Helper function to generate the stars.
 */
float randomGauss() {
    float x1;
    float x2;
    float w = 2.f;

    while (w >= 1.0f) {
        x1 = 2.0f * randf2(0.0f, 1.0f) - 1.0f;
        x2 = 2.0f * randf2(0.0f, 1.0f) - 1.0f;
        w = x1 * x1 + x2 * x2;
    }

    w = sqrtf(-2.0 * logf(w) / w);
    return x1 * w;
}

float gSpeed[12000];

/**
 * Generates the properties for a given star.
 */
void createParticle(Particle_t *part, int idx, float scale) {
    float d = fabsf(randomGauss()) * gGalaxyRadius * 0.5f + randf(64.0f);
    float id = d / gGalaxyRadius;
    float z = randomGauss() * 0.4f * (1.0f - id);
    float p = -d * ELLIPSE_TWIST;

    int r,g,b,a;
    if (d < gGalaxyRadius * 0.33f) {
        r = (int) (220 + id * 35);
        g = 220;
        b = 220;
    } else {
        r = 180;
        g = 180;
        b = (int) clampf(140.f + id * 115.f, 140.f, 255.f);
    }
    // Stash point size * 10 in Alpha
    a = (int) (randf2(1.2f, 2.1f) * 60);
    part->color = r | g<<8 | b<<16 | a<<24;

    if (d > gGalaxyRadius * 0.15f) {
        z *= 0.6f * (1.0f - id);
    } else {
        z *= 0.72f;
    }

    // Map to the projection coordinates (viewport.x = -1.0 -> 1.0)
    d = mapf(-4.0f, gGalaxyRadius + 4.0f, 0.0f, scale, d);

    part->position.x = randf(TWO_PI);
    part->position.y = d;
    gSpeed[idx] = randf2(0.0015f, 0.0025f) * (0.5f + (scale / d)) * 0.8f;

    part->position.z = z / 5.0f;
}

/**
 * Initialize all the stars. Called from Java.
 */
void initParticles() {
    if (gIsPreview == 1) {
        angle = 0.0f;
    }

    Particle_t *part = Particles;
    float scale = gGalaxyRadius / (gWidth * 0.5f);
    for (int i = 0; i < gParticlesCount; i ++) {
        createParticle(part, i, scale);
        part++;
    }
}

void drawSpace() {
    bindTexture(gPFBackground, 0, gTSpace);
    drawQuadTexCoords(
            0.0f, 0.0f, 0.0f, 0.0f, 1.0f,
            gWidth, 0.0f, 0.0f, 2.0f, 1.0f,
            gWidth, gHeight, 0.0f, 2.0f, 0.0f,
            0.0f, gHeight, 0.0f, 0.0f, 0.0f);
}

void drawLights() {
    bindProgramVertex(gPVBkProj);
    bindProgramFragment(gPFBackground);
    bindTexture(gPFBackground, 0, gTLight1);

    float scale = 512.0f / gWidth;
    float x = -scale + gXOffset - scale * 0.05f;
    float y = -scale;

    scale *= 2.0f;

    drawQuad(x, y, 0.0f,
             x + scale * 1.1f, y, 0.0f,
             x + scale * 1.1f, y + scale, 0.0f,
             x, y + scale, 0.0f);
}

void drawParticles(float offset) {
    bindProgramVertex(gPVStars);
    bindProgramFragment(gPFStars);
    bindProgramFragmentStore(gPSLights);
    bindTexture(gPFStars, 0, gTFlares);

    float a = offset * angle;
    float absoluteAngle = fabsf(a);

    float matrix[16];
    matrixLoadTranslate(matrix, 0.0f, 0.0f, 10.0f - 6.0f * absoluteAngle / 50.0f);
    if (gScale == 0) {
        matrixScale(matrix, 6.6f, 6.0f, 1.0f);
    } else {
        matrixScale(matrix, 12.6f, 12.0f, 1.0f);
    }
    matrixRotate(matrix, absoluteAngle, 1.0f, 0.0f, 0.0f);
    matrixRotate(matrix, a, 0.0f, 0.4f, 0.1f);
    vpLoadModelMatrix(matrix);

    // quadratic attenuation
    //pointAttenuation(0.1f + 0.3f * fabsf(offset), 0.0f, 0.06f  + 0.1f *  fabsf(offset));

    Particle_t *vtx = Particles;
    for (int i = 0; i < gParticlesCount; i++) {
        vtx->position.x = vtx->position.x + gSpeed[i];
        vtx++;
    }

    uploadToBufferObject(gParticlesBuffer);
    drawSimpleMeshRange(gParticlesMesh, 0, gParticlesCount);
}

int root() {
    gWidth = getWidth();
    gHeight = getHeight();
    if ((gWidth != gOldWidth) || (gHeight != gOldHeight)) {
        initParticles();
        gOldWidth = gWidth;
        gOldHeight = gHeight;
    }

    float offset = lerpf(-1.0f, 1.0f, gXOffset);
    drawSpace();
    drawParticles(offset);
    drawLights();

    return 45;
}
