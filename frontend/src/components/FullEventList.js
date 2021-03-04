import React, { useState, useEffect } from 'react'
import { getAllEvents,getEventsById } from '../communications/userApi'
import ListElements from "../components/listofevents/ListElements";
import EventsListHeader from "../components/listofevents/EventsListHeader";
import { useLocation } from "react-router-dom";

const FullEventList = (props) => {
    const [events, setEvents] = useState()
    const [error, setError] = useState()
    const location = useLocation()

    const getEvents = async () => {
        const mode = location.state ? location.state : undefined
        try {
            if (mode === undefined) {
                const response = await getAllEvents()
                setEvents(response.data.content)
            } else {
                const response = await getEventsById()
                setEvents(response.data.content)
            }

        } catch (error) {
            setError(error)
        }
    }

    useEffect(() => {
        getEvents()
    }, [])


    return (
        <div className="table-responsive">
            <table className="table table-striped table-sm">
                <EventsListHeader />
                <tbody>
                    {events?.map(event => (
                        <ListElements event={event} />
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default FullEventList;