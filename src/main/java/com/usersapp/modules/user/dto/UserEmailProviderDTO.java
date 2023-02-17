package com.usersapp.modules.user.dto;

public class UserEmailProviderDTO {
	
	private String provider_name;
	
	public UserEmailProviderDTO() {
	}

	public UserEmailProviderDTO(String provider_name) {
		this.provider_name = provider_name;
	}

	public static UserEmailProviderDTO of(String providerName) {
		UserEmailProviderDTO providerDTO = new UserEmailProviderDTO();
		providerDTO.setProvider_name(providerName);
		return providerDTO;
	}
	
	public String getProvider_name() {
		return provider_name;
	}

	public void setProvider_name(String provider_name) {
		this.provider_name = provider_name;
	}
}
