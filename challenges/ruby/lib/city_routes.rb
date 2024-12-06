class CityRoutes
  def self.calculate(origin_destination)
    origin_destination_map = {}

    origin_destination.each do |item|
      origin_destination_map[item[0]] = item[1]
    end

    origin = find_first_origin(origin_destination)
    routes = "#{origin}"
    while origin_destination_map[origin] do
      routes = routes << " -> #{origin_destination_map[origin]}"
      origin = origin_destination_map[origin]
    end

    routes
  end

  def self.find_first_origin(origin_destination)
    origins = origin_destination.map do |item|
      item[0]
    end

    destinations = origin_destination.map do |item|
      item[1]
    end

    first_origin = origins - destinations
    first_origin.first
  end
end
