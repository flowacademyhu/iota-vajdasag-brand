import { render, screen } from '@testing-library/react';
import App from "../App";

test('renders login form correctly', () => {
    render(<App />);
    const titleElement = screen.getByText(/Bejelentkezés/i);
    const emailInput = screen.getByLabelText("Email cím")
    const passwordInput=screen.getByLabelText("Jelszó")
    
    expect(titleElement).toBeInTheDocument();
    expect(emailInput).toBeInTheDocument();
    expect(passwordInput).toBeInTheDocument();

  });
