package springlearning.io.e_transfer_application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springlearning.io.e_transfer_application.dto.TransferRequest;
import springlearning.io.e_transfer_application.dto.TransferResponse;
import springlearning.io.e_transfer_application.entity.Account;
import springlearning.io.e_transfer_application.entity.Transfer;
import springlearning.io.e_transfer_application.entity.TransferStatus;
import springlearning.io.e_transfer_application.entity.User;
import springlearning.io.e_transfer_application.exception.*;
import springlearning.io.e_transfer_application.repository.AccountRepository;
import springlearning.io.e_transfer_application.repository.TransferRepository;
import springlearning.io.e_transfer_application.repository.UserRepository;
import springlearning.io.e_transfer_application.util.IdentifierResolver;
import springlearning.io.e_transfer_application.util.IdentifierType;

import javax.naming.InsufficientResourcesException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService{

    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private TransferRepository transferRepository;

    @Override
    @Transactional
    public TransferResponse transfer(TransferRequest request){

        //TODO: REMOVE THOWS from method signature and write global exception handler

        Transfer existing = transferRepository.findByIdopotencyKey(request.idempotencyKey()).orElse(null);

       if (existing != null) {
           throw new DuplicateTransactionException(existing.getTransactionId());
       }

        Long userId = request.fromUserId();
        String identifier = request.toIdentifier();

        userRepository.findById(userId).orElseThrow(()-> new ProfileNotFoundException(userId));
        IdentifierType resolvedIdentifier =IdentifierResolver.identifierType(identifier);
        User receiver =resolveReceiverProfile(resolvedIdentifier,identifier);

        Account senderAccount = accountRepository.findByUserId(userId).orElseThrow(AccountNotFoundException::new);

        Account receiverAccount = accountRepository.findByUserId(receiver.getId()).orElseThrow(AccountNotFoundException::new);

        if(senderAccount.getBalance().compareTo(request.amount()) < 0){
            throw new InsufficientBalanceException();
        }

        //debit sender

        senderAccount.debit(request.amount());
        //credit receiver

        receiverAccount.credit(request.amount());

        String tnxId = UUID.randomUUID().toString();

        Transfer transfer = Transfer.builder()
                .senderId(userId)
                .receiverId(receiver.getId())
                .transactionId(tnxId)
                .amount(request.amount())
                .status(TransferStatus.SUCCESS)
                .createdAt(LocalDateTime.now())
                .build();

        transferRepository.save(transfer);

        return new TransferResponse(tnxId, "Success");
    }

    private User resolveReceiverProfile(IdentifierType resolvedIdentifier, String identifier) {

        return switch (resolvedIdentifier){
            case EMAIL -> userRepository.findByEmail(identifier)
                    .orElseThrow(() -> new ProfileNotFoundException(identifier,true));
            case PHONE -> userRepository.findByMobile(identifier)
                    .orElseThrow(() -> new ProfileNotFoundException(identifier,true));
        };
    }

}
