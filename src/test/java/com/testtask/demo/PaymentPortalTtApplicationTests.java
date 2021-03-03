package com.testtask.demo;

import com.testtask.demo.model.Account;
import com.testtask.demo.model.Payment;
import com.testtask.demo.model.User;
import com.testtask.demo.model.dto.AccountRequestDto;
import com.testtask.demo.model.dto.AccountResponseDto;
import com.testtask.demo.model.dto.CreatedPaymentResponseDto;
import com.testtask.demo.model.dto.CustomQueryResponseDto;
import com.testtask.demo.model.dto.PaymentRequestDto;
import com.testtask.demo.model.dto.UserRequestDto;
import com.testtask.demo.repository.AccountRepository;
import com.testtask.demo.repository.PaymentRepository;
import com.testtask.demo.repository.UserRepository;
import com.testtask.demo.service.mapper.AccountMapper;
import com.testtask.demo.service.mapper.PaymentMapper;
import com.testtask.demo.service.mapper.UserMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentPortalTtApplicationTests {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PaymentMapper paymentMapper;


    @BeforeAll
    static void beforeAll() {

    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void runUserMapperTest_ok() {
        User user = User.builder().firstName("bob").lastName("alisson").build();
        UserRequestDto requestDto = UserRequestDto.builder().firstName("bob").lastName("alisson").build();
        Assertions.assertEquals(user,userMapper.makeEntity(requestDto));
    }

    @Test
    public void runAccountMapperTest_ok() {
        User user = User.builder().firstName("bob").lastName("alisson").id(15L).build();
        AccountRequestDto requestDto = AccountRequestDto.builder().accountNum("123456")
                .accountType("card/simple").balance(550.0).build();
        Account account = Account.builder().accountType("card/simple")
                .accountNumber("123456").balance(550.0).user(user).build();
        Assertions.assertEquals(account, accountMapper.makeEntity(requestDto, user));
        account.setId(20L);
        AccountResponseDto responseDto = AccountResponseDto.builder().accountId(20L)
                .accountNum("123456").accountType("card/simple").balance(550.0).build();
        Assertions.assertEquals(responseDto, accountMapper.makeDto(account));
    }

    @Test
    public void runPaymentMapperTest_ok() {
        LocalDateTime time = LocalDateTime.of(2020,12,31,23,59);
        User firstUser = User.builder().firstName("bob").lastName("alisson").id(15L).build();
        Account sourceAccount = Account.builder().id(20L).accountType("card/simple")
                .accountNumber("123456").balance(550.0).user(firstUser).build();
        Account destinationAccount = Account.builder().id(15L).accountType("card/simple")
                .accountNumber("987654").balance(550.0).user(firstUser).build();
        Payment payment = Payment.builder().paymentDate(time).amount(50.0).description("test")
                .sourceAccount(sourceAccount).destinationAccount(destinationAccount).build();
        PaymentRequestDto requestDto = PaymentRequestDto.builder().reason("test").amount(50.0)
                .sourceAccId(20L).destAccId(15L).build();
        Payment myPayment = paymentMapper.makeEntity(requestDto, sourceAccount, destinationAccount);
        myPayment.setPaymentDate(time);
        Assertions.assertEquals(payment, myPayment);
        CreatedPaymentResponseDto responseDto = new CreatedPaymentResponseDto();
        myPayment.setStatus("ok");
        myPayment.setId(155L);
        responseDto.setStatus("ok");
        responseDto.setPaymentId(155L);
        Assertions.assertEquals(responseDto, paymentMapper.makeCreatedDto(myPayment));
        User secondUser = User.builder().firstName("John").lastName("Doe").id(99L).build();
        Account secondDestinationAccount = Account.builder().id(5L).accountType("card/simple")
                .accountNumber("111111").balance(550.0).user(secondUser).build();
        myPayment.setDestinationAccount(secondDestinationAccount);
        CustomQueryResponseDto customResponseDto = CustomQueryResponseDto.builder().id(155L)
                .amount(50.0).srcAccNum("123456").destAccNum("111111").status("ok").timestamp(time.toString())
                .payer(CustomQueryResponseDto.Payer.builder().firstName("bob").lastName("alisson").build())
                .recipient(CustomQueryResponseDto.Recipient.builder().firstName("John").lastName("Doe").build())
                .build();
        Assertions.assertEquals(customResponseDto, paymentMapper.makeCustomDto(myPayment));
    }

    @Test
    void runAccountRepositoryTest_ok() {
        User firstUser = User.builder().firstName("bob").lastName("allison").build();
        User secondUser = User.builder().firstName("john").lastName("doe").build();
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        Account firstUserAcc1 = Account.builder().accountNumber("111111").accountType("VISA")
                .balance(100.0).user(firstUser).build();
        Account firstUserAcc2 = Account.builder().accountNumber("222222").accountType("Mastercard")
                .balance(200.0).user(firstUser).build();
        Account secondUserAcc1 = Account.builder().accountNumber("333333").accountType("AE")
                .balance(300.0).user(secondUser).build();
        Account secondUserAcc2 = Account.builder().accountNumber("444444").accountType("simple")
                .balance(400.0).user(secondUser).build();
        accountRepository.saveAll(List.of(firstUserAcc1, firstUserAcc2, secondUserAcc1, secondUserAcc2));
        Optional<Account> actualAcc = accountRepository.getAccountById(1L);
        Assertions.assertEquals(Optional.of(firstUserAcc1), actualAcc);
        List<Account> actualAccs = accountRepository.getAccountsByUser_Id(2L);
        Assertions.assertEquals(List.of(secondUserAcc1, secondUserAcc2), actualAccs);
    }
}
