import React, { useState, useEffect } from 'react';
import { Calendar, dateFnsLocalizer } from 'react-big-calendar';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import startOfWeek from 'date-fns/startOfWeek';
import getDay from 'date-fns/getDay';
import ptBR from 'date-fns/locale/pt-BR';
import 'react-big-calendar/lib/css/react-big-calendar.css'; // CSS Padrão
import './ConsultaCalendar.css'; // Seu CSS customizado (se houver)
// ... (resto da lógica de useEffect para 'events' e localizer igual à versão MUI)

const locales = { /* ... */ };
const localizer = dateFnsLocalizer({ /* ... */ });

function ConsultaCalendar({ consultas, onSelectEvent, onSelectSlot, loading, error }) {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    if (consultas && consultas.length > 0) {
      const calendarEvents = consultas.map(consulta => {
        let startDate, endDate;
        try {
          startDate = new Date(consulta.data);
          endDate = new Date(startDate.getTime() + (60 * 60 * 1000)); 
          if (isNaN(startDate.getTime())) throw new Error("Data inválida");
        } catch (e) {
            console.error(`Erro ao parsear data para consulta ID ${consulta.id}: ${consulta.data}`, e);
            startDate = new Date(); 
            endDate = new Date();
        }
        return {
          id: consulta.id,
          title: `Consulta: ${consulta.cliente?.nome || 'N/D'} com Dr(a). ${consulta.medico?.nome || 'N/D'}`,
          start: startDate,
          end: endDate,
          resource: consulta,
        };
      });
      setEvents(calendarEvents);
    } else {
      setEvents([]);
    }
  }, [consultas]);


  if (loading) {
    return <div className="loading-indicator">Carregando agenda...</div>;
  }
  
  if (error) {
    return <div className="error-message">{error}</div>;
  }

  return (
    <div className="calendar-container"> {/* Wrapper para o calendário */}
      <Calendar
        localizer={localizer}
        events={events}
        startAccessor="start"
        endAccessor="end"
        style={{ height: '70vh' }} // Altura do calendário
        messages={{
            next: "Próximo",
            previous: "Anterior",
            today: "Hoje",
            month: "Mês",
            week: "Semana",
            day: "Dia",
            agenda: "Agenda",
            date: "Data",
            time: "Hora",
            event: "Evento",
            noEventsInRange: "Não há eventos neste período.",
            showMore: total => `+ Ver mais (${total})`
        }}
        onSelectEvent={onSelectEvent}
        onSelectSlot={onSelectSlot}
        selectable
        culture="pt-BR"
      />
    </div>
  );
}

export default ConsultaCalendar;