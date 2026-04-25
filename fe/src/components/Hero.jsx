export default function Hero(
  {id}
) {
  return (
    <section id={id} className="hero">
      <img
        src="/toni 4.png"
        alt="Покана за кръщене"
        className="hero-image"
      />

      <div className="hero-text">
        <h2>Заповядайте на</h2>
        <h2>Светото Кръщение на</h2>
        <h1>Антон</h1>
        <p style={{ fontWeight: "bold", textDecoration: "underline" }}> 31 Май 2026 г.</p>
      </div>
    </section>
  );
}