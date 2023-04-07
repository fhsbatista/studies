Rails.application.routes.draw do
  resources :journal_entries, only: [:create, :index]
end
