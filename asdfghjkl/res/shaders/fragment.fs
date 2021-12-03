#version 330

in vec2 outTexCoord;

out vec4 fragColour;

uniform sampler2D texture_sampler;

void main(){
	fragColour = texture(texture_sampler, outTexCoord);
}