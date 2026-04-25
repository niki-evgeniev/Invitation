import { useEffect, useState } from "react";

export default function EventDetails(
  {id}
) {
  const [timeLeft, setTimeLeft] = useState({});

  useEffect(() => {
    const targetDate = new Date("2026-05-31T11:00:00");

    const interval = setInterval(() => {
      const now = new Date();
      const difference = targetDate - now;

      if (difference <= 0) {
        clearInterval(interval);
        setTimeLeft(null);
        return;
      }

      const days = Math.floor(difference / (1000 * 60 * 60 * 24));
      const hours = Math.floor((difference / (1000 * 60 * 60)) % 24);
      const minutes = Math.floor((difference / 1000 / 60) % 60);
      const seconds = Math.floor((difference / 1000) % 60);

      setTimeLeft({ days, hours, minutes, seconds });
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  return (
    <section className="event" id={id}>
      <h2 className="event-title">Детайли за събитието</h2>

      <div className="event-container">
        <div className="event-card">
          <h3>🕊️ Църква</h3>
          <p>Храм "Света Богородица" - Арбанаси</p>
        </div>

        <div className="event-card">
          <h3>⏰ Час</h3>
          <p>12:00 ч.</p>
        </div>

        <div className="event-card">
          <h3>🍽️ Ресторант</h3>
          <p>Ресторант "Бабенец"</p>
        </div>
        {/* 
        <div className="event-card">
          <h3>📍 Адрес</h3>
          <p>местност Бабенец, 5100 Горна Оряховица</p>
        </div> */}
        <div className="event-card">
          <h3>⏰ Час</h3>
          <p>14:00 ч.</p>
        </div>
      </div>
      <h2 className="event-title" style={{ marginTop: "2%" }}>Оставащо време</h2>

      {/* ⏳ Таймер */}
      {timeLeft && (
        <div className="countdown">
          <div className="time-box">
            <span>{timeLeft.days}</span>
            <p>Дни</p>
          </div>
          <div className="time-box">
            <span>{timeLeft.hours}</span>
            <p>Часа</p>
          </div>
          <div className="time-box">
            <span>{timeLeft.minutes}</span>
            <p>Минути</p>
          </div>
          <div className="time-box">
            <span>{timeLeft.seconds}</span>
            <p>Секунди</p>
          </div>
        </div>
      )}
    </section>
  );
}