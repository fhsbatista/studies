class Contact
  attr_reader :name, :email, :phone
  def initialize(name, email, phone)
    @name = name
    @email = email
    @phone = phone
  end
end

class Event
  attr_reader :description, :date, :hour
  def initialize(description, date, hour)
    @description = description
    @date = date
    @hour = hour
  end
end

class Agenda
  def initialize
    @contacts = []
    @events = []
  end

  def add_contact(contact)
    @contacts.push contact
  end

  def remove_contact(name)
    @contacts.delete_if { |contact| contact.name == name }
  end

  def add_event(event)
    @events.push event
  end

  def remove_event(description)
    @events.delete_if { |event| event.description == description }
  end

  def list_contacts
    puts "-----Contatos-----"
    @contacts.each do |contact|
      puts "Nome: #{contact.name}, Email: #{contact.email}, Phone: #{contact.email}"
    end
    puts "------------------"
  end

  def list_events
    puts "-----Eventos-----"
    @events.each do |event|
      puts "Descrição: #{event.description}, Data: #{event.date}, Hora: #{event.hour}"
    end
    puts "------------------"
  end
end

contact1 = Contact.new "Fernando", "fernando.teste@teste.com", "119191919191"
contact2 = Contact.new "Henrique", "henrique.teste@teste.com", "119191919191"
contact3 = Contact.new "Batista", "batista.teste@teste.com", "119191919191"
event1 = Event.new "Almoço", "01/01/2000", "14hrs"
event2 = Event.new "Treino", "01/01/2000", "16hrs"
event3 = Event.new "Dormir", "01/01/2000", "20hrs"
agenda = Agenda.new
agenda.add_contact contact1
agenda.add_contact contact2
agenda.add_contact contact3
agenda.add_event event1
agenda.add_event event2
agenda.add_event event3
agenda.list_contacts
agenda.list_events
