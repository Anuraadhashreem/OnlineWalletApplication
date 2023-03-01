package com.example.OnlineWalletApplication;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

//WalletDto getWallet(Integer walletId);
@SpringBootTest
class WalletServiceTestWithMock {

	@Autowired
	private CollectWalletService walletService;

	@MockBean
	private CollectWalletRepository walletRepository;



	@Test
	void when_registerWallet_called_then_it_should_register_wallet_and_return_new_wallet() throws WalletException
	{
		//given(this.walletRepository.addWallet(new WalletDto(1,"ammu",2000.0))).willReturn(new WalletDto(1,"ammu",2000.0));

		WalletDto newWallet = new WalletDto(1,"Anu",30000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.addWallet(newWallet)).willReturn(newWallet);

		assertEquals("Anu",this.walletService.registerWallet(newWallet).getWalletUserName());
	}



	@Test
	void when_getWalletTest_called_with_right_id_then_it_should_return_wallet() throws WalletException
	{
		WalletDto wallet = new WalletDto(1,"Anu",30000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.getWalletbyId(1)).willReturn(wallet);
		assertEquals("Anu",this.walletService.getWallet(1).getWalletUserName());

	}

	@Test
	void when_getWallet_called_with_invalid_id_then_returns_WalletException() throws WalletException
	{
		//WalletDto wallet = new WalletDto(1,"Anu",30000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.getWalletbyId(1)).willReturn(null);
		assertThrows(WalletException.class,()->this.walletService.getWallet(2));

	}


	@Test
	void when_replaceWallet_is_called_with_wallet_object_should_return_updatedWallet() throws WalletException
	{
		WalletDto walletToBeUpdataedAs = new WalletDto(1,"Anuraadha",20000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.updateWallet(walletToBeUpdataedAs)).willReturn(walletToBeUpdataedAs);
		given(this.walletRepository.getWalletbyId(1)).willReturn(walletToBeUpdataedAs);
		assertEquals(walletToBeUpdataedAs,this.walletService.replaceWallet(walletToBeUpdataedAs));
	}

	@Test
	void when_replaceWallet_called_with_invalid_id_then_should_return_walletException() throws WalletException
	{
		WalletDto walletToBeUpdataedAs = new WalletDto(1,"Anuraadha",20000.0,"anu@gmail.com","Anu123");
		assertThrows(WalletException.class,()->this.walletService.replaceWallet(walletToBeUpdataedAs));
	}

	@Test
	void when_removeWallet_called_with_valid_id_then_should_return_walletObject_deleted() throws WalletException
	{
		WalletDto walletToBeDeleted = new WalletDto(1,"Anuraadha",20000.0,"anu@gmail.com","Anu123");

		given(this.walletRepository.deleteWallet(1)).willReturn(walletToBeDeleted);

		given(this.walletRepository.getWalletbyId(1)).willReturn(walletToBeDeleted);

		assertEquals(walletToBeDeleted,this.walletService.removeWallet(1));
	}


    @Test
	void when_addFundToWallet_called_then_should_return_walletAdded() throws WalletException
	{
		WalletDto walletToBeUpdated = new WalletDto(1,"Anuraadha",1000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.getWalletbyId(1)).willReturn(walletToBeUpdated);
		given(this.walletRepository.updateWallet(walletToBeUpdated)).willReturn(walletToBeUpdated);
		assertEquals(1500.0,this.walletService.addFundToWalletId(1,500.0).getBalance());


	}

	@Test
	void when_fundTransfer_called_with_valid_ids_then_should_return_true() throws WalletException
	{
		WalletDto walletFrom = new WalletDto(1,"Anuraadha",1000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.getWalletbyId(1)).willReturn(walletFrom);
		given(this.walletRepository.updateWallet(walletFrom)).willReturn(walletFrom);

		WalletDto walletTo = new WalletDto(2,"Theju",1000.0,"Theju@gmail.com","Theju123");
		given(this.walletRepository.getWalletbyId(2)).willReturn(walletTo);
		given(this.walletRepository.updateWallet(walletTo)).willReturn(walletTo);

		assertEquals(true,this.walletService.fundTransfer(walletFrom.getWalletId(),walletTo.getWalletId(),500.0));
	}

	@Test
	void when_fundTransfer_called_with_invalid_ids_or_insufficient_balance_or_with_same_ids_then_returns_walletException() throws WalletException
	{
		WalletDto walletFrom = new WalletDto(1,"Anuraadha",1000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.getWalletbyId(1)).willReturn(walletFrom);
		given(this.walletRepository.updateWallet(walletFrom)).willReturn(walletFrom);

		WalletDto walletTo = new WalletDto(2,"Theju",1000.0,"Theju@gmail.com","Theju123");
		given(this.walletRepository.getWalletbyId(2)).willReturn(walletTo);
		given(this.walletRepository.updateWallet(walletTo)).willReturn(walletTo);
      //  WalletException ex = this.walletService.fundTransfer(walletFrom.getWalletId(),walletTo.getWalletId(),1500.0);
		assertThrows(WalletException.class,()->this.walletService.fundTransfer(walletFrom.getWalletId(),walletTo.getWalletId(),1500.0));
	}

	@Test
	void withdrawalTest() throws WalletException
	{
		WalletDto walletFrom = new WalletDto(1,"Anuraadha",1000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.getWalletbyId(1)).willReturn(walletFrom);
		given(this.walletRepository.updateWallet(walletFrom)).willReturn(walletFrom);

		assertEquals(500,this.walletService.fundWithdrawalById(1,500.0));

	}

	@Test
	void withdrawalTestException() throws WalletException
	{
		WalletDto walletFrom = new WalletDto(1,"Anuraadha",1000.0,"anu@gmail.com","Anu123");
		given(this.walletRepository.getWalletbyId(1)).willReturn(walletFrom);
		given(this.walletRepository.updateWallet(walletFrom)).willReturn(walletFrom);

		assertThrows(WalletException.class,()->this.walletService.fundWithdrawalById(1,5000.0));

	}




}
