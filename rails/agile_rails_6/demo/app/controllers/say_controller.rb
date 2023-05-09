class SayController < ApplicationController
  def hello
    @time = Time.new
    @files = Dir.glob("*")
  end

  def goodbye
    @files = Dir.glob("*")
  end
end
