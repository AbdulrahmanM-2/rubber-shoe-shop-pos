export default function UserTable({ users }) {
  if (!users || users.length === 0) return <p>No users found.</p>;

  return (
    <table className="w-full table-auto border-collapse">
      <thead>
        <tr className="bg-gray-200">
          <th className="border px-2 py-1">User ID</th>
          <th className="border px-2 py-1">Name</th>
          <th className="border px-2 py-1">Username</th>
          <th className="border px-2 py-1">Role</th>
        </tr>
      </thead>
      <tbody>
        {users.map(u => (
          <tr key={u.id} className="hover:bg-gray-100">
            <td className="border px-2 py-1">{u.id}</td>
            <td className="border px-2 py-1">{u.fullName}</td>
            <td className="border px-2 py-1">{u.username}</td>
            <td className="border px-2 py-1">{u.role}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
