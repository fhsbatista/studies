require "test_helper"

class ProductTest < ActiveSupport::TestCase
  test "product attributes must now be empty" do
    product = Product.new
    assert product.invalid?
    assert product.errors[:title].any?
    assert product.errors[:description].any?
    assert product.errors[:price].any?
    assert product.errors[:image_url].any?
  end

  test "product price must be positive" do
    product = Product.new(
      title: "My book title",
      description: "some description",
      image_url: "https://myimage.com/image.png",
    )
    product.price = -1
    assert product.invalid?
    assert_equal ["must be greater than or equal to 0.01"], product.errors[:price]

    product.price = 1
    assert product.valid?
  end

  test "product image_url must be valid" do
    invalid_url = %w{ fred.gif FRED.GIF http: http:// www.goo.l/lls.jpg 123 test23 }
    valid_url = %w{ https://teste.com/fred.gif https://some.br/FRED.GIF http://teste.com/image }

    invalid_url.each do |image_url|
      product = Product.new(
        title: "My book title",
        description: "some description",
        image_url: image_url,
        price: 1,
      )
      assert product.invalid?, "#{image_url} shouldn't be valid"
    end

    valid_url.each do |image_url|
      product = Product.new(
        title: "My book title",
        description: "some description",
        image_url: image_url,
        price: 1,
      )
      assert product.valid?, "#{image_url} shouldn't be invalid"
    end
  end
end
