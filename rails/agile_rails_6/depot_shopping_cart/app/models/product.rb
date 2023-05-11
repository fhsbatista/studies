require 'uri'
class Product < ApplicationRecord
  validates :title, :description, :image_url, presence: true
  validates :price, numericality: { greater_than_or_equal_to: 0.01 }
  validates :title, uniqueness: true
  validates :image_url, format: {
    with: URI::DEFAULT_PARSER.make_regexp(%w[http https]),
    message: 'must be a valid URL.'
  }
end
