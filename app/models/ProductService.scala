package models

trait ProductService{
  def findAll() = {
    Product.listProducts()
  }
}

object ProductService extends ProductService{

}
