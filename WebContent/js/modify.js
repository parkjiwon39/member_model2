/**
 * 
 */
$(function(){
	$("#modifyform").validate({
		rules:{
			current_password:{
				required:true
			},
			new_password:{
				required:true,
				validPWD:true				
			},
			confirm_password:{
				required:true,
				validPWD:true,
				equalTo:"#new_password"
			}
		},
		messages:{
			current_password:{
				required:"현재 비밀번호를 입력해주세요."
			},
			new_password:{
				required:"새로운 비밀번호를 입력해주세요."			
			},
			confirm_password:{
				required:"새로운 비밀번호를 다시 입력해주세요.",
				equalTo:"비밀번호가 다릅니다.다시 입력해주세요."
			}
		}
	})
	$.validator.addMethod("validPWD",function(value){
		const regPWD =/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,15}$/;			
		return regPWD.test(value);
	},"비밀번호는 영문자,숫자,특수문자의 조합으로8~15자리로 만들어야 합니다.");
})