import { render, screen } from '@testing-library/react';
import App from "../App";

test('renders login form correctly', () => {
    render(<App />);
    const titleElement = screen.getByText("Sign In");
    const emailInput = screen.getByLabelText("Email")
    const passwordInput=screen.getByLabelText("Password")
    
    expect(titleElement).toBeInTheDocument();
    expect(emailInput).toBeInTheDocument();
    expect(passwordInput).toBeInTheDocument();

  });
