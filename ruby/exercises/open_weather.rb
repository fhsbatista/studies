require 'net/http'
require 'json'

class OpenWeather
  API_URL = 'http://api.openweathermap.org/data/2.5/weather'

  def initialize(api_key)
    @api_key = api_key
  end

  def build_url(city)
    encoded_city = URI.encode_www_form_component city
    return URI("#{API_URL}?q=#{encoded_city}&appid=#{@api_key}")
  end

  def get_response(url)
    return Net::HTTP.get_response(url)
  end

  def get_weather(city)
    url = build_url(city)
    res = get_response(url)

    if res.is_a?(Net::HTTPSuccess)
      data = JSON.parse(res.body)
      weather = data['weather'][0]['description']
      return "O clima em #{city.capitalize} é: #{weather}"
    else
      return "Não foi possível obter o clima para #{city.capitalize}"
    end
  end
end

open_weather = OpenWeather.new(api_key: 'e0334c94e957625c54e3488e8d10d658')
puts open_weather.get_weather 'Monte Aprazível'