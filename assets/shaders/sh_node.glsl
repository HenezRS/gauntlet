#version 120
attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec4 v_color;
varying vec2 v_texCoords;

void main() {
    v_color = a_color;
    v_texCoords = a_texCoord0;
    gl_Position = u_projTrans * a_position;
}

    //-!@#frag
    #version 120
varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;
uniform vec3 colorPrimary;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords).rgba;
    float gray = (color.r + color.g + color.b) / 3.0;
    if(gray>0.2f) {
        gl_FragColor = vec4(colorPrimary,color.a);
    } else {
        gl_FragColor = vec4(color);
    }
}