
export interface PaymentDenominations{
  change: number;
  changeAsDenominators: number[];
}

export interface TFPaymentRequest{
  cartTotal: number;
  moneyInserted: number[];
}

export interface TFPaymentResponse{
  status: string;
  code: string | null;
  error: string | null;
  data: PaymentDenominations
}

export interface Denomination {
  value: number;
  label: string;
  count: number;
}

