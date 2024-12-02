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
            if !row.iter().is_sorted_by(|a, b| a < b) && !row.iter().is_sorted_by(|a, b| a > b) {
                return false;
            }
            let mut is_safe = true;
            let mut prev = None;
            for elt in row {
                if prev.is_none() {
                    prev = Some(elt);
                    continue;
                }
                let level_diff = (prev.unwrap() - elt).abs();
                is_safe = level_diff > 0 && level_diff < 4;
                prev = Some(elt);
                if !is_safe {
                    break;
                }
            }
            is_safe
        })
        .count() as i32
}

fn day2_part2(input: &str) -> i32 {
    0
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
}

const TEST_INPUT: &str = include_str!("test_input.txt");
const INPUT: &str = include_str!("input.txt");
