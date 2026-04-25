export default function MapSection(
  {id}
) {
  return (
    <section className="map-section" id={id}>
      <h2 className="map-title">Локации</h2>

      <div className="map-container">
        {/* Църква */}
        <div className="map-card">
          <h3>🕊️ Църква – 12:00ч</h3>
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2913.3096254537554!2d25.65732737655025!3d43.098007588161366!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40a9289606cfdead%3A0x5097bc64188dbd08!2z0JzQsNC90LDRgdGC0LjRgCDigJ7QodCy0LXRgtCwINCR0L7Qs9C-0YDQvtC00LjRhtCw4oCc!5e0!3m2!1sbg!2sbg!4v1774302510442!5m2!1sbg!2sbg"
            allowFullScreen=""
            loading="lazy"
          ></iframe>
        </div>

        {/* Ресторант */}
        <div className="map-card">
          <h3>🍽️ Ресторант – 14:00ч</h3>
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2911.7884305762273!2d25.6717127!3d43.1299705!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40a92965aee347eb%3A0xe82341a6a6b1bfe7!2z0JrQvtC80L_Qu9C10LrRgSDQkdCw0LHQtdC90LXRhg!5e0!3m2!1sbg!2sbg!4v1774302433958!5m2!1sbg!2sbg"
            allowFullScreen=""
            loading="lazy"
          ></iframe>
        </div>
      </div>
    </section>
  );
}