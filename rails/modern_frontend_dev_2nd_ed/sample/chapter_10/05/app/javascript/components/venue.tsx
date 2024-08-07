import * as React from "react"
import VenueBody from "./venue_body"
import VenueHeader from "./venue_header"
import { createConsumer, Subscription } from "@rails/actioncable"

interface VenueProps {
  concertId: number
  rowCount: number
  seatsPerRow: number
}

export interface TicketData {
  id: number
  row: number
  number: number
  status: string
}
export type RowData = TicketData[]
export type VenueData = RowData[]

let subscription: Subscription

const Venue = ({
  concertId,
  rowCount,
  seatsPerRow,
}: VenueProps): React.ReactElement => {
  const [ticketsToBuyCount, setTicketsToBuyCount] = React.useState(1)
  const [venueData, setVenueData] = React.useState<VenueData>([])

  React.useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`/tickets.json?concert_id=${concertId}`)
      const json = await response.json()
      setVenueData(json)
    }
    fetchData()
  }, [])

  if (subscription === undefined) {
    subscription = createConsumer().subscriptions.create(
      { channel: "ConcertChannel", concertId: concertId },
      {
        received(data) {
          setVenueData(data)
        },
      }
    )
  }

  return (
    <>
      <VenueHeader
        seatsPerRow={seatsPerRow}
        setTicketsToBuyCount={setTicketsToBuyCount}
      />
      <VenueBody
        concertId={concertId}
        seatsPerRow={seatsPerRow}
        rowCount={rowCount}
        subscription={subscription}
        ticketsToBuyCount={ticketsToBuyCount}
        venueData={venueData}
      />
    </>
  )
}

export default Venue
