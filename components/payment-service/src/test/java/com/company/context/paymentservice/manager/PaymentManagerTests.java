package com.company.context.paymentservice.manager;

import com.company.context.paymentservice.data.Payment;
import com.company.context.paymentservice.data.PaymentStatus;
import com.company.context.paymentservice.domain.PaymentDTO;
import com.company.context.paymentservice.domain.PaymentDetailDTO;
import com.company.context.paymentservice.service.PaymentService;
import com.company.context.paymentservice.web.intf.PaymentRequest;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentManagerTests {

  private static double totalAmount = 20.00d;
  private static String currency =  "USD";
  private static String orderUuid = UUID.randomUUID().toString();
  private static String paymentUuid = UUID.randomUUID().toString();
  private static PaymentStatus paymentStatus = PaymentStatus.SUBMITTED;

  @InjectMocks
  PaymentManager paymentManager;

  @Mock
  PaymentService paymentService;

  @BeforeEach
  void beforeEachTest(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("New payment - Positive")
  public void newPaymentPositive(){
    PaymentDTO paymentDTO = getPaymentDTO();
    Mockito.when(paymentService.createPayment(Mockito.any(Payment.class))).thenReturn(paymentDTO);
    PaymentRequest paymentRequest = new PaymentRequest();
    paymentRequest.setOrderUuid(orderUuid);
    paymentRequest.setPaymentStatus(paymentStatus);
    paymentRequest.setAmount(totalAmount);
    paymentRequest.setCurrency(currency);

    PaymentDTO result = paymentManager.newPayment(paymentRequest);
    Assertions.assertEquals(paymentDTO.getOrderUuid(), result.getOrderUuid());
    Assertions.assertEquals(paymentDTO.getPaymentDetails(), result.getPaymentDetails());
  }

  @Test
  @DisplayName("Get payment by uuid - Positive")
  public void getPaymentByUUIDPositive(){
    PaymentDTO paymentDTO = getPaymentDTO();
    Mockito.when(paymentService.findPaymentByUuid(Mockito.anyString())).thenReturn(paymentDTO);

    PaymentDTO result = paymentManager.getPayment(paymentUuid);
    Assertions.assertEquals(paymentDTO.getUuid(), result.getUuid());
    Assertions.assertEquals(paymentDTO.getOrderUuid(), result.getOrderUuid());
    Assertions.assertEquals(paymentDTO.getPaymentDetails(), result.getPaymentDetails());
  }

  @Test
  @DisplayName("Get payment by order uuid - Positive")
  public void getPaymentByOrderUUIDPositive(){
    PaymentDTO paymentDTO = getPaymentDTO();
    Mockito.when(paymentService.findPaymentByOrderUuid(Mockito.anyString())).thenReturn(paymentDTO);

    PaymentDTO result = paymentManager.getPaymentByOrderUuid(orderUuid);
    Assertions.assertEquals(paymentDTO.getUuid(), result.getUuid());
    Assertions.assertEquals(paymentDTO.getOrderUuid(), result.getOrderUuid());
    Assertions.assertEquals(paymentDTO.getPaymentDetails(), result.getPaymentDetails());
  }

  @Test
  @DisplayName("Update payment status - Positive")
  public void updatePaymentStatusPositive(){
    PaymentDTO paymentDTO = getPaymentDTO();
    paymentDTO.setStatus(PaymentStatus.COMPLETED);
    Mockito.when(paymentService.updatePaymentStatus(Mockito.anyString(), Mockito.any(PaymentStatus.class))).thenReturn(paymentDTO);

    PaymentDTO result = paymentManager.updatePaymentStatus(paymentUuid, PaymentStatus.COMPLETED);
    Assertions.assertEquals(paymentDTO.getUuid(), result.getUuid());
    Assertions.assertEquals(paymentDTO.getOrderUuid(), result.getOrderUuid());
    Assertions.assertEquals(paymentDTO.getPaymentDetails(), result.getPaymentDetails());
  }

  @Test
  @Tag("gradle-skip")
  @DisplayName("Fails if not skipped!")
  void failsEveryTime(){
    Assertions.fail("This is supposed to fail, but this should have been skipped because of the tag");
  }

  private PaymentDTO getPaymentDTO() {
    PaymentDTO paymentDTO = new PaymentDTO();
    paymentDTO.setUuid(paymentUuid);
    paymentDTO.setStatus(paymentStatus);
    PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO();
    paymentDetailDTO.setTotalAmount(totalAmount);
    paymentDetailDTO.setCurrency(currency);
    List<PaymentDetailDTO> paymentDetailDTOs = new ArrayList<>();
    paymentDetailDTOs.add(paymentDetailDTO);
    paymentDTO.setPaymentDetails(paymentDetailDTOs);
    return paymentDTO;
  }

}
