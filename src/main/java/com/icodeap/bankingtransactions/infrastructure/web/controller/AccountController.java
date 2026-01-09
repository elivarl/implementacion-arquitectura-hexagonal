package com.icodeap.bankingtransactions.infrastructure.web.controller;

import com.icodeap.bankingtransactions.application.dto.CreateAccountCommand;
import com.icodeap.bankingtransactions.application.dto.DepositMoneyCommand;
import com.icodeap.bankingtransactions.application.dto.WithdrawMoneyCommand;
import com.icodeap.bankingtransactions.application.port.CreateAccountUseCase;
import com.icodeap.bankingtransactions.application.port.DepositMoneyUseCase;
import com.icodeap.bankingtransactions.application.port.GetAccountDetailsUseCase;
import com.icodeap.bankingtransactions.application.port.WithdrawMoneyUseCase;
import com.icodeap.bankingtransactions.infrastructure.web.dto.AccountResponse;
import com.icodeap.bankingtransactions.infrastructure.web.dto.CreateAccountRequest;
import com.icodeap.bankingtransactions.infrastructure.web.dto.DepositRequest;
import com.icodeap.bankingtransactions.infrastructure.web.dto.WithdrawRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final DepositMoneyUseCase depositMoneyUseCase;
    private final WithdrawMoneyUseCase withdrawMoneyUseCase;
    private final GetAccountDetailsUseCase getAccountDetailsUseCase;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest request){
        var command = new CreateAccountCommand(request.customerId(), request.initialBalance());
        var dto = createAccountUseCase.createAccount(command);
        return ResponseEntity.status(201).body(AccountResponse.fromDto(dto));
    }

    @PostMapping("/{id}/deposit")
    public  ResponseEntity<AccountResponse> deposit(@PathVariable String id, @Valid @RequestBody DepositRequest request){
        var command = new DepositMoneyCommand(id, request.amount());
        var dto = depositMoneyUseCase.deposit(command);
        return ResponseEntity.ok(AccountResponse.fromDto(dto));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@PathVariable String id, @Valid @RequestBody WithdrawRequest withdrawRequest){
        var command = new WithdrawMoneyCommand(id, withdrawRequest.amount());
        var dto = withdrawMoneyUseCase.withdraw(command);
        return ResponseEntity.ok(AccountResponse.fromDto(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable String id){
        var dto = getAccountDetailsUseCase.getById(id);
        return ResponseEntity.ok(AccountResponse.fromDto(dto));
    }

}
