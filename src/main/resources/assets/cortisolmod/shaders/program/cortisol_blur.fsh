#version 150

uniform sampler2D DiffuseSampler;

uniform float RADIUS;
in vec2 texCoord;
out vec4 fragColor;

#define PI 3.141592
// QUALITY SETTINGS :
// high quality : (32, 32)
// medium quality : (16, 16)
// low quality : (8, 8)
// POTATO : (4, 4)
#define SAMPLES_THETA 4.
#define SAMPLES_K 4.

#define GAUSSIAN

vec3 blur(sampler2D tex, vec2 uv, float radius) {
    vec3 c = texture(tex, uv).rgb;
    float w = 1.;

    for(float theta = 0.; theta < 2. * PI; theta += 2. * PI / SAMPLES_THETA) {
        for(float r = 1. / SAMPLES_K; r < 1.; r += 1. / SAMPLES_K) {

            vec2 sample_pos = uv + vec2(cos(theta), sin(theta)) * r * radius;

            #ifdef GAUSSIAN
            float pow_factor = 1. - r;
            float w_sample = 1. / (r * r + 1.) * pow_factor * pow_factor;
            #else
            float pow_factor = 1. - r;
            float w_sample = exp(- r) * pow_factor * pow_factor;
            #endif

            c += texture(tex, sample_pos).rgb * w_sample;
            w += w_sample;
        }
    }
    return c / w;
}
void main() {
    float l = length(texCoord - vec2(0.5));
    l = smoothstep(0.1, 0.7, l) * l;
    vec3 col = blur(DiffuseSampler, texCoord, RADIUS * l);
    //vec4 tex = texture(DiffuseSampler, texCoord);
    fragColor = vec4(col, 1.0);

}