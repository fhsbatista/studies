class Intersection
  # O(n + m), n = array1 length, m = array2 length
  # O(2 n) for short
  def self.find(array1, array2)
    array_1_as_map = {}
    array1.each do |e|
      array_1_as_map[e] = true
    end

    intersection = []

    array2.each do |e|
      intersection << e if array_1_as_map[e] != nil
    end

    intersection
  end
end
