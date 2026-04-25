import { useState } from "react";

export default function RSVP(
    {id}
) {
    const [guestsCount, setGuestsCount] = useState(1);
    const [guests, setGuests] = useState([]);

    const handleGuestsChange = (count) => {
        setGuestsCount(count);

        const additionalGuests = count - 1; // 🔥 ключово

        const newGuests = [];
        for (let i = 0; i < additionalGuests; i++) {
            newGuests.push({ firstName: "", lastName: "" });
        }

        setGuests(newGuests);
    };

    const handleGuestInput = (index, field, value) => {
        const updated = [...guests];
        updated[index][field] = value;
        setGuests(updated);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const data = {
            email: e.target.email.value,
            mainFirstName: e.target.firstName.value,
            mainLastName: e.target.lastName.value,
            message: e.target.message.value,
            guests: guests,
        };

        console.log("RSVP DATA:", data);
        let serverText = '';
        try {
            const response = await fetch("http://localhost:8585/addGuest", {
            // const response = await fetch("/api/addGuest", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

            const result = await response.text();
            serverText = result;
            if (!response.ok) {
                throw new Error("Server error");
            }


            console.log("Server response:", result);

            alert("Благодарим! Потвърждението е изпратено.");
            e.target.reset();
            setGuests([]);

        } catch (error) {
            console.error(error);
            if (serverText === "Error : Email exist") {
                alert("Емайлът е регистриран");
            } else {
                alert("Грешка при изпращането. v2");
            }

        }
    };


    return (
        <section className="rsvp" id={id}>
            <h2>Потвърди присъствие</h2>

            <form className="rsvp-form" onSubmit={handleSubmit}>
                {/* Основен човек */}
                <input name="firstName" placeholder="Име" required />
                <input name="lastName" placeholder="Фамилия" required />
                <input name="email" type="email" placeholder="Имейл" required />

                {/* Брой хора */}
                <select
                    value={guestsCount}
                    onChange={(e) => handleGuestsChange(Number(e.target.value))}
                >
                    <option value={1}>Само аз</option>
                    <option value={2}>Аз + 1 човек</option>
                    <option value={3}>Аз + 2 човека</option>
                    <option value={4}>Аз + 3 човека</option>
                </select>

                {/* Динамични полета */}
                {guests.map((guest, index) => (
                    <div key={index} className="guest-row">
                        <input
                            placeholder={`Име на гост ${index + 2}`}
                            value={guest.firstName}
                            onChange={(e) =>
                                handleGuestInput(index, "firstName", e.target.value)
                            }
                            required
                        />
                        <input
                            placeholder={`Фамилия на гост ${index + 2}`}
                            value={guest.lastName}
                            onChange={(e) =>
                                handleGuestInput(index, "lastName", e.target.value)
                            }
                            required
                        />
                    </div>
                ))}

                <textarea
                    name="message"
                    placeholder="Съобщение (по желание)"
                    rows="4"
                />

                <button type="submit">Потвърди</button>
            </form>
            <h2>
                или позвъни на
                <br />
                <a href="tel:+359896450701" style={{ color: "#2f3e2f", textDecoration: "none" }}>

                    Христина: <i className="fa-solid fa-phone" style={{ marginRight: "8px" }}></i><u>0896450701</u>
                </a>
                <br />
                <a href="tel:+359899524251" style={{ color: "#2f3e2f", textDecoration: "none" }}>

                    Николай: <i className="fas fa-phone me-2"></i> <u>0899524251</u>
                </a>
            </h2>
        </section>
    );
}