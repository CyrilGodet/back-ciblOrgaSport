import os
import re

base_dir = r'/home/khadija/GLOP/back-ciblOrgaSport/src/main/java/com/glop/cibl_orga_sport'
sql_file = r'/home/khadija/GLOP/back-ciblOrgaSport/seed_data.sql'

replacements = {
    r'\bEquipe\b': 'ParticipantEquipe',
    r'\bequipe\b': 'participantEquipe',
    r'\bequipes\b': 'participantEquipes',
    r'\bEquipes\b': 'ParticipantEquipes',
    r'\bEQUIPE\b': 'PARTICIPANT_EQUIPE'
}

rename_map = {
    'Equipe.java': 'ParticipantEquipe.java',
    'EquipeDTO.java': 'ParticipantEquipeDTO.java',
    'EquipeMapper.java': 'ParticipantEquipeMapper.java',
    'EquipeRepository.java': 'ParticipantEquipeRepository.java',
    'EquipeService.java': 'ParticipantEquipeService.java',
    'EquipeServiceImpl.java': 'ParticipantEquipeServiceImpl.java',
    'EquipeController.java': 'ParticipantEquipeController.java'
}

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    new_content = content
    for pattern, repl in replacements.items():
        new_content = re.sub(pattern, repl, new_content)

    if new_content != content:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(new_content)
        print(f"Updated {filepath}")

for root, dirs, files in os.walk(base_dir):
    for filename in files:
        if filename.endswith('.java'):
            filepath = os.path.join(root, filename)
            process_file(filepath)
            
            if filename in rename_map:
                new_filepath = os.path.join(root, rename_map[filename])
                os.rename(filepath, new_filepath)
                print(f"Renamed {filepath} to {new_filepath}")

with open(sql_file, 'r', encoding='utf-8') as f:
    sql_content = f.read()
for pattern, repl in replacements.items():
    sql_content = re.sub(pattern, repl, sql_content)
with open(sql_file, 'w', encoding='utf-8') as f:
    f.write(sql_content)
print(f"Updated {sql_file}")
