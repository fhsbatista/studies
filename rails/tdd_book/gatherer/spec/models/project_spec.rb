require "rails_helper"

RSpec.describe Project do
  let(:project) { Project.new }
  let(:task) { Task.new }
  it "considers a project with tasks to be done" do
    expect(project).to be_done
  end

  it "considers a project with an incomplete task is not done" do
    project.tasks << task
    expect(project).not_to be_done
  end
end
