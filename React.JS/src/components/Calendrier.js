import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import React, { Component } from 'react';
class Calendrier extends Component{
    render(){
        return(
            <div>
                <h1>Calendrier Page</h1>
                <FullCalendar plugins={[dayGridPlugin]}
                initialView='dayGridMonth'
                events={[
                    { title: 'Etre vraiment cool', date: '2023-09-20' },
                    { title: 'Etre encore plus cool', date: '2023-09-25' }
                  ]}
                />
            </div>
        );
    }
}
export default Calendrier;