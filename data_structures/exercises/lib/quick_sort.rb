def quick_sort(array)
  return array if array.length <= 1

  pivot = array.first

  left = []
  right = []
  middle = []

  array.each do |e|
    if e < pivot
      left << e
    elsif e > pivot
      right << e
    else
      middle << e
    end
  end

  quick_sort(left) + middle + quick_sort(right)
end

p quick_sort([1, 5, 3, 20, 25, 12, 30, -3, -5])
