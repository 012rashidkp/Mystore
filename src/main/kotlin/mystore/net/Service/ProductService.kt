package mystore.net.Service

import mystore.net.Model.Products
import mystore.net.Requests.CreateProductParams

interface ProductService {

    suspend fun createproduct(params:CreateProductParams):Products?



}