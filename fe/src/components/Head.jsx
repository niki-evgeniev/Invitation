export default function Head() {
  console.log("open web")
  return (
    <header className="header">
      <div className="header-container">
        <nav className="nav">
          <a href="#home">Начало</a>
          <a href="#event">Събитие</a>
          <a href="#location">Локация</a>
          <a href="#confirm">Присъствие</a>
        </nav>
      </div>
    </header>
  );

}