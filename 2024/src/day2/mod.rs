pub fn day2() {
    let part1_result = day2_part1(INPUT);
    println!("Day 2, Part 1: {}", part1_result);
    let part2_result = day2_part2(INPUT);
    println!("Day 2, Part 2: {}", part2_result);
}

fn day2_part1(input: &str) -> i32 {
    let input = parse_input(input);
    input
        .iter()
        .filter(|&row| {
            is_safe(row, None)
        })
        .count() as i32
}

fn day2_part2(input: &str) -> i32 {
    let input = parse_input(input);
    input
        .iter()
        .filter(|&row| {
            is_safe(row, None) || (0..row.len()).into_iter().any(|idx| is_safe(row, Some(idx)))
        })
        .count() as i32
}

fn is_safe(row: &Vec<i32>, skip_idx: Option<usize>) -> bool {
    let is_ascending = row[0] < row[1];
    let mut is_safe = true;
    let mut prev = None;
    for (idx, cur) in row.iter().enumerate() {
        if Some(idx) == skip_idx {
            continue;
        }
        if prev.is_none() {
            prev = Some(cur);
            continue;
        }
        let level_diff = (prev.unwrap() - cur).abs();
        let safe_direction = if is_ascending {
            prev.unwrap() < cur
        } else {
            prev.unwrap() > cur
        };
        is_safe = level_diff > 0 && level_diff < 4 && safe_direction;
        prev = Some(cur);
        if !is_safe {
            break;
        }
    }
    is_safe
}

fn parse_input(input: &str) -> Vec<Vec<i32>> {
    input
        .lines()
        .map(|line| {
            line.split_whitespace()
                .map(|x| x.parse::<i32>().unwrap())
                .collect::<Vec<i32>>()
        })
        .collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_part1() {
        let result = day2_part1(TEST_INPUT);
        assert_eq!(result, 2);
    }

    #[test]
    fn test_part2() {
        let result = day2_part2(TEST_INPUT);
        assert_eq!(result, 4);
    }
}

const TEST_INPUT: &str = include_str!("test_input.txt");
const INPUT: &str = include_str!("input.txt");
