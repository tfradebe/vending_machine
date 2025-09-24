export interface Product{
  id: number;
  name: string;
  location: string;
  price: number;
  quantity: number;
}

export interface ApiResponse{
  status: string;
  code: string | null;
  error: string | null;
  data: Product[];
}
