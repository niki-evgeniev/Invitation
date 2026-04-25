export default function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">
        <p>Очакваме Ви на този специален ден ❤️</p>
        <p className="footer-names">Николай, Христина и Антон</p>

        <div className="footer-divider"></div>

        <p className="footer-date"><b><u>31 Май 2026</u></b></p>
      </div>
      <div className="container" style={{ marginTop: "3%" }}>
        <div className="row align-items-center">
          <div className="col-md-6 text-center text-md-start">
            Created by:{" "}
            <a
              href="https://m.facebook.com/Psyhopath/"
              target="_blank"
              rel="noopener noreferrer"
              style={{ color: "white", textDecoration: "none" }}
            >
              Nikolay Ivanov
            </a>
          </div>

        </div>
      </div>
    </footer>
  );
}