#!/usr/bin/env python3
"""
Script untuk menambahkan exception handling pada semua file geometri
yang belum diupdate.
"""

import os
import re

def add_exception_handling_to_file(file_path):
    """Menambahkan exception handling ke file Java"""
    
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Skip jika sudah ada exception handling
    if 'ERROR_NEGATIVE' in content and 'try {' in content:
        print(f"✓ {file_path} sudah memiliki exception handling")
        return
    
    # Tambahkan constants untuk validasi
    if 'private static final double MIN_VALUE' not in content:
        # Cari posisi setelah package dan import
        package_match = re.search(r'package.*?;', content)
        if package_match:
            insert_pos = package_match.end()
            constants = '''
    
    private static final double MIN_VALUE = 0.0;
    private static final String ERROR_NEGATIVE = "Nilai tidak boleh negatif atau nol";'''
            content = content[:insert_pos] + constants + content[insert_pos:]
    
    # Update constructor dengan try-catch
    constructor_pattern = r'public\s+(\w+)\s*\(([^)]*)\)\s*\{'
    constructor_match = re.search(constructor_pattern, content)
    
    if constructor_match:
        class_name = constructor_match.group(1)
        params = constructor_match.group(2)
        
        # Buat validation method
        validation_method = f'''
    
    private void validateInput({params}) {{
        {generate_validation_logic(params)}
    }}'''
        
        # Update constructor
        new_constructor = f'''    public {class_name}({params}) {{
        try {{
            validateInput({extract_param_names(params)});
            {extract_constructor_body(content, constructor_match)}
        }} catch (IllegalArgumentException e) {{
            throw new IllegalArgumentException("Error membuat {class_name}: " + e.getMessage());
        }}
    }}'''
        
        # Replace constructor
        content = re.sub(constructor_pattern, new_constructor, content)
        
        # Add validation method
        if 'private void validateInput' not in content:
            content = content.replace('    public ' + class_name + '(', validation_method + '\n\n    public ' + class_name + '(')
    
    # Update calculation methods dengan try-catch
    content = add_try_catch_to_methods(content)
    
    # Add getInfo method if not exists
    if 'public String getInfo()' not in content:
        content = add_get_info_method(content, class_name)
    
    # Write back to file
    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(content)
    
    print(f"✓ Updated {file_path}")

def generate_validation_logic(params):
    """Generate validation logic based on parameters"""
    param_names = extract_param_names(params)
    validation_lines = []
    
    for param in param_names:
        if param.strip():
            validation_lines.append(f'if ({param.strip()} <= MIN_VALUE) {{')
            validation_lines.append(f'    throw new IllegalArgumentException("{param.strip()} " + ERROR_NEGATIVE + ": " + {param.strip()});')
            validation_lines.append('}')
    
    return '\n        '.join(validation_lines)

def extract_param_names(params):
    """Extract parameter names from parameter string"""
    if not params.strip():
        return []
    
    param_names = []
    for param in params.split(','):
        param = param.strip()
        if param:
            # Extract variable name (remove type)
            name = param.split()[-1]
            param_names.append(name)
    
    return param_names

def extract_constructor_body(content, constructor_match):
    """Extract constructor body"""
    start = constructor_match.end()
    brace_count = 1
    end = start
    
    while brace_count > 0 and end < len(content):
        if content[end] == '{':
            brace_count += 1
        elif content[end] == '}':
            brace_count -= 1
        end += 1
    
    body = content[start:end-1].strip()
    # Remove validation calls if they exist
    body = re.sub(r'validateInput\([^)]*\);?\s*', '', body)
    return body

def add_try_catch_to_methods(content):
    """Add try-catch blocks to calculation methods"""
    
    # Pattern untuk method hitungLuas dan hitungKeliling
    method_patterns = [
        r'public double hitungLuas\(\)\s*\{([^}]+)\}',
        r'public double hitungKeliling\(\)\s*\{([^}]+)\}',
        r'public double hitungVolume\(\)\s*\{([^}]+)\}',
        r'public double hitungLuasPermukaan\(\)\s*\{([^}]+)\}'
    ]
    
    for pattern in method_patterns:
        matches = re.finditer(pattern, content)
        for match in matches:
            method_body = match.group(1).strip()
            if 'try {' not in method_body:
                method_name = re.search(r'hitung(\w+)', pattern).group(1)
                new_body = f'''
        try {{
            {method_body}
        }} catch (Exception e) {{
            throw new RuntimeException("Error menghitung {method_name.lower()}: " + e.getMessage());
        }}'''
                
                content = content.replace(match.group(0), 
                                        match.group(0).replace(method_body, new_body))
    
    return content

def add_get_info_method(content, class_name):
    """Add getInfo method if not exists"""
    
    # Find the end of the class (before the last closing brace)
    last_brace = content.rfind('}')
    if last_brace != -1:
        get_info_method = f'''

    public String getInfo() {{
        try {{
            return String.format("{class_name}{{...}}", /* add parameters here */);
        }} catch (Exception e) {{
            return "Error mendapatkan info {class_name.lower()}: " + e.getMessage();
        }}
    }}

    @Override
    public String toString() {{
        return getInfo();
    }}'''
        
        content = content[:last_brace] + get_info_method + content[last_brace:]
    
    return content

def main():
    """Main function to update all geometry files"""
    
    # Directories to process
    directories = [
        'src/main/java/Geometri/benda2D',
        'src/main/java/Geometri/benda3D'
    ]
    
    # Files that have already been updated
    updated_files = {
        'Lingkaran.java',
        'Persegi.java', 
        'PersegiPanjang.java',
        'Segitiga.java',
        'Trapesium.java',
        'Bola.java',
        'JuringBola.java',
        'Tabung.java',
        'CincinBola.java'
    }
    
    for directory in directories:
        if os.path.exists(directory):
            print(f"\nProcessing {directory}:")
            for filename in os.listdir(directory):
                if filename.endswith('.java') and filename not in updated_files:
                    file_path = os.path.join(directory, filename)
                    try:
                        add_exception_handling_to_file(file_path)
                    except Exception as e:
                        print(f"✗ Error updating {file_path}: {e}")

if __name__ == "__main__":
    main() 