package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.model.detail.*;
import com.example.demo.model.mapper.TransactionMapper;;
import com.example.demo.repository.*;
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

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreHaveProductRepository storeHaveProductRepository;
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

    @Override
    public TransactionDto createTransactionMobile(int id_user, int quantity, int product) {

        Transaction transaction = new Transaction();
        transaction.setUser(userRepository.findById(id_user).get());
        transaction.setCreate_at(new Date(System.currentTimeMillis()));
        //status = 0: dang giao hang
        transaction.setStatus(0);

        transaction = transactionRepository.save(transaction);

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setQuantity(quantity);
        transactionDetail.setProduct(productRepository.findById(product).get());
        transactionDetail.setCreate_at(new Date(System.currentTimeMillis()));
        transactionDetail.setTotal_money(productRepository.findById(product).get().getPrice()*quantity);
        transactionDetail.setTransaction(transaction);
        transactionDetailRepository.save(transactionDetail);

        transaction.setTotal_price(transactionDetail.getTotal_money());
        transactionRepository.save(transaction);

        List<Store> stores = storeRepository.findAll();

        for(Store store : stores){
            int id = store.getId();
            StoreHaveProduct s = storeHaveProductRepository.findStoreHaveProduct(id).get(0);

            if(quantity<=s.getQuantity()){
                s.setQuantity(s.getQuantity()-quantity);
                break;
            }

        }
        return TransactionMapper.toTransactionDto(transactionRepository.save(transaction));
    }

    @Override
    public ListTransactionDto getAllTransactionForStore(int page) {
        try {
            Page<Transaction> rs = transactionRepository.listTransactionForStore(PageRequest.of(page,6));
            List<Transaction> transactions = rs.getContent();
            List<TransactionDto> listTransactionDto = new ArrayList<>();
            for(Transaction t : transactions){
                listTransactionDto.add(TransactionMapper.toTransactionDto(t));

            }
            ListTransactionDto list = new ListTransactionDto();
            list.setTotalItems(rs.getTotalElements());
            list.setTotalPages(rs.getTotalPages());
            list.setTransactionDtos(listTransactionDto);
            return list;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public ListTransactionDto getAllTransactionForUser(int user_id, int page) {
        try {
            Page<Transaction> rs = transactionRepository.listTransactionForCustomer(user_id,PageRequest.of(page,6));
            List<Transaction> transactions = rs.getContent();
            List<TransactionDto> listTransactionDto = new ArrayList<>();
            for(Transaction t : transactions){
                listTransactionDto.add(TransactionMapper.toTransactionDto(t));

            }
            ListTransactionDto list = new ListTransactionDto();
            list.setTotalItems(rs.getTotalElements());
            list.setTotalPages(rs.getTotalPages());
            list.setTransactionDtos(listTransactionDto);
            return list;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public TransactionDto updateStatusOfOrder(int order_id, int status) {
        Transaction transaction = transactionRepository.findById(order_id).get();
        if(status==0||status == 1 || status == 2){
            transaction.setStatus(status);
        }
        return TransactionMapper.toTransactionDto(transaction);

    }


}
