#!/bin/bash

# Go to frontend folder
cd frontend || { echo "❌ Error: frontend folder not found"; exit 1; }

echo "✅ Current directory: $(pwd)"

PACKAGE_JSON="package.json"
TEMP_JSON="package_temp.json"

# 1️⃣ Auto-fix package.json: remove comments & trailing commas
echo "🛠️ Auto-fixing package.json..."
cat "$PACKAGE_JSON" \
  | sed 's://.*$::' \
  | sed '/\/\*/,/\*\//d' \
  | sed -E 's/,( *[\]}])/\\1/g' > "$TEMP_JSON"

# 2️⃣ Validate JSON
if ! node -e "JSON.parse(require('fs').readFileSync('$TEMP_JSON','utf-8'))" 2>/dev/null; then
  echo "❌ package.json still invalid after auto-fix!"
  rm -f "$TEMP_JSON"
  exit 1
fi
mv "$TEMP_JSON" "$PACKAGE_JSON"
echo "✅ package.json fixed and valid"

# 3️⃣ Ensure essential dependencies exist
echo "🔧 Checking essential dependencies..."
node -e "
const fs = require('fs');
let pkg = JSON.parse(fs.readFileSync('$PACKAGE_JSON','utf-8'));
pkg.dependencies = pkg.dependencies || {};
pkg.devDependencies = pkg.devDependencies || {};

const deps = {
  'react': '^18.2.0',
  'react-dom': '^18.2.0',
  'react-scripts': '^5.0.1',
  'axios': '^1.6.0'
};
const devDeps = {
  'tailwindcss': '^3.3.3',
  'postcss': '^8.4.35',
  'autoprefixer': '^10.4.14',
  'eslint': '^8.47.0'
};

for(const k in deps) if(!pkg.dependencies[k]) pkg.dependencies[k]=deps[k];
for(const k in devDeps) if(!pkg.devDependencies[k]) pkg.devDependencies[k]=devDeps[k];

fs.writeFileSync('$PACKAGE_JSON', JSON.stringify(pkg,null,2));
console.log('✅ Essential dependencies added/verified');
"

# 4️⃣ Remove old node_modules & lock file
echo "🧹 Cleaning node_modules and package-lock.json..."
rm -rf node_modules package-lock.json

# 5️⃣ Clean npm cache
echo "🧹 Cleaning npm cache..."
npm cache clean --force

# 6️⃣ Install dependencies
echo "📦 Installing dependencies..."
npm install || { echo "❌ npm install failed"; exit 1; }

# 7️⃣ Build project
echo "🏗️ Building frontend..."
npm run build || { echo "❌ npm build failed"; exit 1; }

echo "🎉 Frontend fully prepared: package.json fixed, dependencies installed, and build successful!"
