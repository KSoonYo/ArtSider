package com.ssafy.myapp.api.request;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class UserLoginPostReq {

	String email;
	String password;
}
