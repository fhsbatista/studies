require "test_helper"

class OrderMailerTest < ActionMailer::TestCase
  test "received" do
    mail = OrderMailer.received(orders(:one))
    assert_equal "Depot store order confirmation", mail.subject
    assert_equal ["example@gmail.com"], mail.to
    assert_equal ["depot@store.com"], mail.from
  end

  test "shipped" do
    mail = OrderMailer.shipped(orders(:one))
    assert_equal "Depot store order shipped", mail.subject
    assert_equal ["example@gmail.com"], mail.to
    assert_equal ["depot@store.com"], mail.from
  end
end
