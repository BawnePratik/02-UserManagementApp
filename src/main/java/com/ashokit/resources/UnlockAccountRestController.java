package com.ashokit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashokit.model.UnlockAccount;
import com.ashokit.service.UserMasterService;

@RestController
public class UnlockAccountRestController {

	@Autowired
	private UserMasterService userService;
	
	@PostMapping("/unlockAcc")
	public String unlockUserAccount(@RequestBody UnlockAccount unlockAcc) {
		if(userService.isTempPwdValid(unlockAcc.getEmail(), unlockAcc.getTempPwd())) {
			userService.unlockAccount(unlockAcc.getEmail(), unlockAcc.getNewPwd());
			return "Account is Unlocked, You can now proceed with Login Process";
		}else {
			return "Please Enter Valid Temporary Password";
		}
	}	
}
