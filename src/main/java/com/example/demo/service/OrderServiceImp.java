package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Transaction;
import com.example.demo.entity.TransactionDetail;
import com.example.demo.entity.User;
import com.example.demo.model.detail.CartItemDto;
import com.example.demo.model.detail.TransactionDetailDto;
import com.example.demo.model.detail.TransactionDto;
import com.example.demo.model.mapper.TransactionMapper;;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.TransactionDetailRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderServiceImp implements OrderService {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Override
    public TransactionDto createTransaction(int id_user) {
        try {
            List<CartItemDto> cartItemDtoList = cartItemService.getAllCartItem(id_user);
            List<TransactionDetail> transactionDetails = new ArrayList<>();

            Transaction transaction = new Transaction();
            transaction.setUser(userRepository.findById(id_user).get());
            transaction.setCreate_at(new Date(System.currentTimeMillis()));
            //status = 0: dang giao hang
            transaction.setStatus(0);

            transaction = transactionRepository.save(transaction);
            for(CartItemDto cartItemDto : cartItemDtoList){
                TransactionDetail transactionDetail = new TransactionDetail();
                transactionDetail.setProduct(productRepository.findById(cartItemDto.getProduct_id()).get());
                transactionDetail.setQuantity(cartItemDto.getQuantity());
                transactionDetail.setCreate_at(new Date(System.currentTimeMillis()));
                transactionDetail.setTotal_money(productRepository.findById(cartItemDto.getProduct_id()).get().getPrice()*cartItemDto.getQuantity());
                transactionDetail.setTransaction(transaction);
                transactionDetailRepository.save(transactionDetail);
                transaction.setTotal_price(transaction.getTotal_price() + transactionDetail.getTotal_money());
            }

            return TransactionMapper.toTransactionDto(transactionRepository.save(transaction));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<TransactionDto> listTransaction(int id_user,int page) {
        Page<Transaction> rs = transactionRepository.listTransactionByUser(id_user, PageRequest.of(page, 5));
        List<Transaction> transactionList = rs.getContent();

        List<TransactionDto> transactionDtoList = new ArrayList<>();
        for(Transaction transaction : transactionList){
            transactionDtoList.add(TransactionMapper.toTransactionDto(transaction));
        }
        return transactionDtoList;
    }

    @Override
    public List<TransactionDetailDto> getListTransactionDetail(int transaction_id) {
        List<TransactionDetailDto> transactionDetailDtoList = new ArrayList<>();
        List<TransactionDetail> transactionDetails = transactionDetailRepository.findTransactionDetailByTransactionId(transaction_id);
        for(TransactionDetail transactionDetail : transactionDetails){
            transactionDetailDtoList.add(TransactionMapper.transactionDetailDto(transactionDetail));
        }
        return transactionDetailDtoList;
    }


}
