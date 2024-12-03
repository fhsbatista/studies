module BinarySearch
  def self.search(array, target)
    left = 0
    right = array.length - 1

    while (left <= right)
      mid = (left + right) / 2

      return mid if array[mid] == target

      if array[mid] < target
        left = mid + 1
      else
        right = mid - 1
      end
    end

    return -1
  end
end
