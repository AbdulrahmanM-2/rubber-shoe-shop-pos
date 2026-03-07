export default function UserProfile({ user }) {
  if (!user) return <p>No user profile found.</p>;

  return (
    <div className="border p-4 rounded shadow-md w-full max-w-md">
      <h3 className="text-xl font-bold mb-2">{user.fullName}</h3>
      <p><strong>Username:</strong> {user.username}</p>
      <p><strong>Email:</strong> {user.email}</p>
      <p><strong>Role:</strong> {user.role}</p>
    </div>
  );
}
