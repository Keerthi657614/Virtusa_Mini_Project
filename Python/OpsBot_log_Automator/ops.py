import os
from datetime import datetime

def scan_logs(source_file):
    alert_terms = ["ERROR", "CRITICAL", "FAILED LOGIN"]
    summary = {term: 0 for term in alert_terms}
    alerts = []
    try:
        with open(source_file, "r") as log:
            for entry in log:
                for term in alert_terms:
                    if term in entry:
                        summary[term] += 1
                        alerts.append(entry)
                        break
    except FileNotFoundError:
        print("Log file not found. Please check the file name.")
        return None, None
    return summary, alerts
def generate_report(alert_lines):
    today = datetime.now().strftime("%Y-%m-%d")
    report_name = f"security_alert_{today}.txt"
    with open(report_name, "w") as report:
        report.writelines(alert_lines)
    return report_name
def display_summary(stats, report_file):
    print("\nSecurity Alert Report")
    for category, count in stats.items():
        print(f"{category} occurrences: {count}")
    size = os.path.getsize(report_file)
    print(f"\nReport generated: {report_file}")
    print(f"Report size: {size} bytes")
log_file = "server.log"
counts, matched_logs = scan_logs(log_file)
if counts is not None:
    output_file = generate_report(matched_logs)
    display_summary(counts, output_file)
