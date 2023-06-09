require "test_helper"

class OrderMailerTest < ActionMailer::TestCase
  test "received" do
    mail = OrderMailer.received(orders(:one))
    assert_equal "Depot store order confirmation", mail.subject
    assert_equal ["depot@store.com"], mail.to
    assert_equal ["depot@store.com"], mail.from
    assert_match "/1 x Keyboard1", mail.body.encoded
  end

  test "shipped" do
    mail = OrderMailer.shipped(orders(:one))
    assert_equal "Depot store order shipped", mail.subject
    assert_equal ["depot@store.com"], mail.to
    assert_equal ["depot@store.com"], mail.from
    assert_match "Keyboard1", mail.body.encoded
  end

end
