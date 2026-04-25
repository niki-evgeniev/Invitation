import EventDetails from "./components/EventDetails";
import Footer from "./components/Footer";
import Head from "./components/Head"
import Hero from "./components/Hero";
import MapSection from "./components/MapSection";
import Rsvp from "./components/Rsvp";
import "./index.css";

function App() {


  return (
    <>
      <Head />
      <Hero id="home"/>
      <EventDetails id="event"/>
      <MapSection id="location"/>
      <Rsvp id="confirm"/>
      <Footer />
    </>
  )
}

export default App
