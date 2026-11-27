# Testing

## November 27, 2026

Branch: `feature/category-filtering`

### Category Filtering Tests

| Test | Expected Result | Actual Result | Status |
|---|---|---|---|
| Filter Food | $17.50 | $17.50 | Pass |
| Filter food | $17.50 | $17.50 | Pass |
| Filter Transport | $3.50 | $3.50 | Pass |
| Filter Entertainment | No matching expenses | No matching expenses | Pass |
| Filter School | $4.00 | $4.00 | Pass |
| Empty category | Error message | Error message | Pass |

### MVP Regression Tests

| Test | Expected Result | Actual Result | Status |
|---|---|---|---|
| Compile application | No errors | No errors | Pass |
| Add Notebook | Expense added | Expense added | Pass |
| View expenses | 4 expenses | 4 expenses | Pass |
| Calculate total | $25.00 | $25.00 | Pass |
| Restart application | 4 expenses loaded | 4 expenses loaded | Pass |
| Nonnumeric amount | Rejected | Rejected | Pass |
| Negative amount | Rejected | Rejected | Pass |
| Invalid date | Rejected | Rejected | Pass |
| Comma in expense name | Rejected | Rejected | Pass |
| Invalid menu choice | Error message | Error message | Pass |
| Total after invalid inputs | $25.00 | $25.00 | Pass |
| Exit application | Normal exit | Normal exit | Pass |

### Exception Handling Tests

| Test | Expected Result | Actual Result | Status |
|---|---|---|---|
| Interrupted menu input | Graceful exit | Graceful exit | Pass |
| Interrupted expense input | Graceful exit | Graceful exit | Pass |
| Interrupted category input | Graceful exit | Graceful exit | Pass |

### Conclusion

Category filtering was successfully implemented and manually tested. The existing MVP features continued to work after the new feature was added. Invalid inputs were handled without crashing the application or changing the recorded expenses. All tests listed above passed before merging the feature branch into main.