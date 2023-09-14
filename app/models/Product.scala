package models

case class Product(productId: Int,
                   productType: String,
                   colour: String,
                   availability: Boolean,
                   size: String)


object Product  {
  def listProducts():List[Product] = List(
    Product(1111,"shirt","white", availability = true,"S"),
    Product(1112,"trouser","white", availability = true,"M"),
    Product(1113,"trouser","white", availability = true,"M"),
    Product(1114,"trouser","white", availability = true,"M"),
    Product(1115,"shirt","white", availability = true,"L"),
    Product(1116,"shirt","white", availability = true,"L"),
    Product(1117,"shirt","white", availability = true,"L"),
    Product(1118,"shirt","white", availability = false,"L")
  )
}